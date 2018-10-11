package com.alibaba.core.concurrent.scheduling.support;

import com.alibaba.core.concurrent.CustomizableThreadFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.lang.Nullable;

import java.util.concurrent.*;

/**
 * @author sier.pys 9/25/18
 */
public abstract class ExecutorConfigurationSupport extends CustomizableThreadFactory implements BeanNameAware, DisposableBean, InitializingBean {

    protected final Log logger = LogFactory.getLog(getClass());


    private ThreadFactory threadFactory = this;

    private RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();

    private boolean threadNamePrefixSet = false;

    private boolean waitForTasksToCompleteOnShutdown = false;

    private int awaitTerminationSeconds = 0;

    @Nullable
    private String beanName;

    @Nullable
    private ExecutorService executorService;

    public void setThreadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory == null ? this : threadFactory;
    }

    @Override
    public void setThreadNamePrefix(String threadNamePrefix) {
        super.setThreadNamePrefix(threadNamePrefix);
        this.threadNamePrefixSet = true;
    }

    public void setRejectedExecutionHandler(RejectedExecutionHandler rejectedExecutionHandler) {
        this.rejectedExecutionHandler = (rejectedExecutionHandler == null ? new ThreadPoolExecutor.AbortPolicy() : rejectedExecutionHandler);
    }

    public void setWaitForTasksToCompleteOnShutdown(boolean waitForTasksToCompleteOnShutdown) {
        this.waitForTasksToCompleteOnShutdown = waitForTasksToCompleteOnShutdown;
    }

    public void setAwaitTerminationSeconds(int awaitTerminationSeconds) {
        this.awaitTerminationSeconds = awaitTerminationSeconds;
    }

    @Override
    public void setBeanName(@Nullable String beanName) {
        this.beanName = beanName;
    }


    @Override
    public void destroy() throws Exception {
        shutdown();
    }


    public abstract ExecutorService initializeExecutor(ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler);

    @Override
    public void afterPropertiesSet() throws Exception {
        initialize();
    }

    private void initialize() {
        if (logger.isInfoEnabled()) {
            logger.info("Initialize ExecutorService " + (this.beanName == null ? "" : " '" + this.beanName + "'"));
        }
        if (!this.threadNamePrefixSet && this.beanName != null) {
            setThreadNamePrefix(this.beanName);
        }
        this.executorService = initializeExecutor(threadFactory, rejectedExecutionHandler);
    }

    private void shutdown() {
        if (logger.isInfoEnabled()) {
            logger.info("Shutting down ExecutorSerivce " + (this.beanName != null ? " '" + this.beanName + "'" : ""));
        }
        if (this.executorService != null) {
            if (this.waitForTasksToCompleteOnShutdown) {
                this.executorService.shutdown();
            } else {
                for (Runnable remainingTask : this.executorService.shutdownNow()) {
                    cancelRemaingTask(remainingTask);
                }
            }
            awaitTerminationIfNecessary(this.executorService);
        }
    }

    protected void cancelRemaingTask(Runnable task) {
        if (task instanceof Future) {
            ((Future) task).cancel(true);
        }
    }

    private void awaitTerminationIfNecessary(ExecutorService executorService) {
        if (this.awaitTerminationSeconds > 0) {
            try {
                if (!executorService.awaitTermination(this.awaitTerminationSeconds, TimeUnit.SECONDS)) {
                    if (logger.isWarnEnabled()) {
                        logger.warn("Timed out while waiting for executor"
                                + (this.beanName != null ? " '" + this.beanName + "'" : "") + " to terminate");
                    }
                }
            } catch (InterruptedException e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Interrupted while waiting for executor"
                            + (this.beanName != null ? " '" + this.beanName + "'" : "") + " to terminate");
                }
                Thread.currentThread().interrupt();
            }
        }
    }

}
