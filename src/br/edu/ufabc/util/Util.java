/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufabc.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class Util {

    public static List<String> filePaths = new ArrayList<>();

    public static void lerNomeArquivos() {
        // Use assim (exemplo): 

        String dir = "/Users/charleshenriqueportoferreira/Documentos/";

        File diretorio = new File("Users/charlehenriqueportoferreira");
        File[] fList = diretorio.listFiles();

        System.out.println("Numero de arquivos no diretorio : " + fList.length);
        for (File fList1 : fList) {
            System.out.println(fList1.toString());
        }
    }

    public static List<String> fileTreePrinter(File initialPath, int initialDepth) {

        int depth = initialDepth++;
        if (initialPath.exists()) {
            File[] contents = initialPath.listFiles();
            for (File content : contents) {
                if (content.isDirectory()) {
                    fileTreePrinter(content, initialDepth + 1);
                } else {
                    char[] dpt = new char[initialDepth];
                    for (int j = 0; j < initialDepth; j++) {
                        dpt[j] = '+';
                    }
                    // System.out.println(new String(dpt) + content.getName() + " " + content.getPath() );
                    //System.out.println(content.toString());
                    if (content.getName().contains(".txt")) {
                        filePaths.add(content.toString());
                    }

                }
            }
        }
        return filePaths;
    }

    public static String lerArquivo(String filePath) throws FileNotFoundException, IOException {
        StringBuilder linha = new StringBuilder();
        try (FileReader fr = new FileReader(filePath); BufferedReader br = new BufferedReader(fr)) {
            while (br.ready()) {
                linha.append(br.readLine());
                linha.append("\n");
            }
            br.close();
            fr.close();
        }
        return linha.toString();
    }

    public static String insertStopListTag(Set<String> verbos) {
        StringBuilder stopList = new StringBuilder();
        for (String verbo : verbos) {
            stopList.append("<stopword>").append(verbo).append("</stopword>").append("\n");
        }
        return stopList.toString();
    }

    public static void printFile(String fileName, String texto) throws IOException {
        try (FileWriter fw = new FileWriter(fileName); BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(texto);

            bw.newLine();

            bw.close();
            fw.close();
        }
    }

}
