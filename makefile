
UNAME = $(shell uname)

ifeq ($(UNAME), Linux)
ANTLR_HOME = ~/Applications
else 
ANTLR_HOME = /usr/local/lib
endif

ANTLR_JAR = ${ANTLR_HOME}/antlr-4.9.1-complete.jar

ANTLR = java -jar ${ANTLR_JAR}
GRUN = java org.antlr.v4.gui.TestRig

GRAMMAR = Cor.g4
PYTHON_PATH = ./python_out/gen
JAVA_PATH = ./java_out

ASSEMBLER = ./python_out/assembler.py
EXAMPLE  = ./programs/example.cor



all: build_python parse_python

test: build_java test_java

.PHONY: all test build_python build_java parse_python test_java

build_python: ${GRAMMAR}
	${ANTLR} -Dlanguage=Python3 -o ${PYTHON_PATH} ${GRAMMAR}

parse_python: ${ASSEMBLER} ${EXAMPLE}
	python3 ${ASSEMBLER} ${EXAMPLE}

build_java: ${GRAMMAR}
	${ANTLR} -o ${JAVA_PATH} ${GRAMMAR}; \
	javac -d ${JAVA_PATH} ${JAVA_PATH}/Cor*.java

test_java:
	cd ${JAVA_PATH}; \
	${GRUN} Cor parse ../programs/example.cor -gui
