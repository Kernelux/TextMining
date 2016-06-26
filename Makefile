all: compile run

compile:
	javac -sourcepath src/ -d build/classes src/Main.java
run:
	java -classpath build/classes/ Main
clean:
	rm -rf build/
