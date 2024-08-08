package com.fahad.sec04.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class FileReaderServiceImpl implements FileReaderService{

    private static final Logger log = LoggerFactory.getLogger(FileReaderServiceImpl.class);

    @Override
    public Flux<String> read(Path path) {

//        public Mono<String> read(String fileName) {
//            return Mono.fromCallable(()-> Files.readString(PATH.resolve(fileName)));
//        }
        /*Flux.<String>generate(synchronousSink -> {
                    var country = Util.faker().country().name();
                    synchronousSink.next(country);

                })
                .takeUntil(c->c.equalsIgnoreCase("pakistan"))
                .subscribe(Util.subscriber());*/
      /*  Flux.generate(synchronousSink -> {
            try {
                Stream<String> fileStream = Files.lines(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        })

    }
*/
        return Flux.generate(
                ()-> openFile(path),
                this::readFile,this::closeFile
        );
    }

    private BufferedReader openFile(Path path) throws IOException {
        log.info("opening file");
        return Files.newBufferedReader(path);
    }

    private BufferedReader readFile(BufferedReader reader, SynchronousSink<String> sink){
        String line = null;
        try {
            line = reader.readLine();
            log.info("reading line {} ", line);
            if(Objects.isNull(line)){
                sink.complete();
            }else {
                sink.next(line);
            }
        } catch (IOException e) {
            sink.error(e);
        }
        return reader;
    }

    private void closeFile(BufferedReader reader){
        try{
            reader.close();
            log.info("closed file");
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
