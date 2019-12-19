package com.alibaba.calcite;

import org.apache.calcite.linq4j.Enumerator;
import org.apache.calcite.util.Source;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author sier.pys 2019/12/16
 */
public class CsvEnumerator<E> implements Enumerator<E> {
    private E current;
    private BufferedReader br;


    public CsvEnumerator(Source source) {
        try {
            this.br = new BufferedReader(source.reader());
            this.br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public E current() {
        return current;
    }

    @Override
    public boolean moveNext() {
        try {
            String line = br.readLine();
            if (line == null) {
                return false;
            }

            current = (E) line.split(",");    // ����Ƕ��У�����Ҫ���ֵ
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void reset() {
        System.out.println("�������ֵܣ���֧�ִ˲���");
    }

    @Override
    public void close() {

    }
}
