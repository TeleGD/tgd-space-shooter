build:
	mkdir -p bin
	javac -d bin -cp src:.:lib/* src/fr/main/Main.java

run:
	java -cp bin:/:lib/* -Djava.library.path=natives fr.main.Main

clean:
	rm -r -f bin/*

.PHONY: build run clean
