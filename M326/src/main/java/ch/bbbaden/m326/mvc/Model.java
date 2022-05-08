package ch.bbbaden.m326.mvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.util.Observable;


public class Model extends Observable {


    private int countNumbers = 0;
    private int sumNumbers = 0;
    private int maxNumber = Integer.MIN_VALUE;
    private int minNumber = Integer.MAX_VALUE;



    public void addNumber(int number) {
        countNumbers++;
        sumNumbers += number;
        maxNumber = Math.max(number, maxNumber);
        minNumber = Math.min(number, minNumber);
        setChanged();
        notifyObservers();
    }

    public int getCountNumbers() {
        return countNumbers;
    }

    public int getSumNumbers() {
        return sumNumbers;
    }

    public double getAverageNumbers() {
        return sumNumbers / (double) countNumbers;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public int getMinNumber() {
        return minNumber;
    }
}
