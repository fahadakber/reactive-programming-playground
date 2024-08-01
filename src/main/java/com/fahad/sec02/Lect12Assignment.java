package com.fahad.sec02;

import com.fahad.common.Util;
import com.fahad.sec02.assignment.FileServiceImpl;

public class Lect12Assignment {
    public static void main(String[] args) {
        var fileService = new FileServiceImpl();

        fileService.write("file.txt", "Hello Fahad!")
                        .subscribe(Util.subscriber());

        fileService.read("file.txt")
                .subscribe(Util.subscriber());

        fileService.delete("file.txt")
                .subscribe(Util.subscriber());
    }
}
