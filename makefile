
ANTLR_HOME = /usr/local/lib
ANTLR_JAR = ${ANTLR_HOME}/antlr-4.9.1-complete.jar

ANTLR = java -jar ${ANTLR_JAR}
GRUN = java org.antlr.v4.gui.TestRig

GRAMMAR = Cor.g4
PYTHON_PATH = ./assembler/gen
JAVA_PATH = ./java_gen

ASSEMBLER = ./assembler/assembler.py
EXAMPLE  = ./programs/example.cor


all: build_python parse_python

test: build_java test_java

clean: clean_java clean_python

.PHONY: all test clean build_python build_java parse_python test_java clean_java clean_python

build_python: ${GRAMMAR}
	${ANTLR} -Dlanguage=Python3 -o ${PYTHON_PATH} ${GRAMMAR}

parse_python: ${ASSEMBLER} ${EXAMPLE}
	python3 ${ASSEMBLER} ${EXAMPLE}

build_java: ${GRAMMAR}
	${ANTLR} -o ${JAVA_PATH} ${GRAMMAR}; \
	javac -d ${JAVA_PATH} ${JAVA_PATH}/Cor*.java

test_java: ${EXAMPLE}
	cd ${JAVA_PATH}; \
	${GRUN} Cor parse .${EXAMPLE} -gui

clean_python:
	@ if [ -d "${PYTHON_PATH}" ]; then rm -r ${PYTHON_PATH}; fi

clean_java:
	@ if [ -d "${JAVA_PATH}" ]; then rm -r ${JAVA_PATH}; fi
