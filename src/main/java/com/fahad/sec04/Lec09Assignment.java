package com.fahad.sec04;

import com.fahad.common.Util;
import com.fahad.sec04.assignment.FileReaderService;
import com.fahad.sec04.assignment.FileReaderServiceImpl;

import java.nio.file.Path;

public class Lec09Assignment {
    public static void main(String[] args) {
        var path = Path.of("src/main/resources/sec04/file.txt");
        var fileRederService = new FileReaderServiceImpl();
        fileRederService.read(path)
                //.take(6) // only first 6 lines
                //.takeUntil(line->line.equalsIgnoreCase("line04"))  // keep on reading until you see line 04
                .subscribe(Util.subscriber());

    }
}
