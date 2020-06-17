package com.velotn.Entite;

import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.awt.print.Book;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Don {
    private int id;
    private double somme;
    private LocalDate date;
    private int userId;

    public Don(int id) {
        this.id = id;
    }

    public Don(int id, double somme) {
        this.id = id;
        this.somme = somme;
    }

    public Don(double somme, int userId) {
        this.date = LocalDate.now().plusDays(1);
        this.somme = somme;
        this.userId = userId;
    }

    public Don(int id, double somme, LocalDate date, int userId) {
        this.id = id;
        this.somme = somme;
        this.date = date;
        this.userId = userId;
    }

    public Don(double somme,LocalDate date, int userId) {
        this.somme = somme;
        this.date = LocalDate.now();
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSomme() {
        return somme;
    }

    public void setSomme(double somme) {
        this.somme = somme;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "com.velotn.Entite.Don{" +
                "id=" + id +
                ", somme=" + somme +
                ", date='" + date + '\'' +
                ", userId=" + userId +
                '}';
    }

    /*CellProcessor[] processors = new CellProcessor[] {
            new ParseDouble(), // price
            new FmtDate("MM/dd/yyyy"), // published date
    };*/

    static void writeCSVFile(String csvFileName, List<Don> listDons) {
        ICsvBeanWriter beanWriter = null;
        CellProcessor[] processors = new CellProcessor[] {
                new ParseDouble(), // price, // publisher
                new FmtDate("MM/dd/yyyy") // published date

        };

        try {
            beanWriter = new CsvBeanWriter(new FileWriter(csvFileName),
                    CsvPreference.STANDARD_PREFERENCE);
            String[] header = {"date", "somme"};
            beanWriter.writeHeader(header);

            for (Don aBook : listDons) {
                beanWriter.write(aBook, header, processors);
            }

        } catch (IOException ex) {
            System.err.println("Error writing the CSV file: " + ex);
        } finally {
            if (beanWriter != null) {
                try {
                    beanWriter.close();
                } catch (IOException ex) {
                    System.err.println("Error closing the writer: " + ex);
                }
            }
        }
    }
}
