# Configuration file for the Sphinx documentation builder.
#
# This file only contains a selection of the most common options. For a full
# list see the documentation:
# https://www.sphinx-doc.org/en/master/usage/configuration.html

# -- Path setup --------------------------------------------------------------

# If extensions (or modules to document with autodoc) are in another directory,
# add these directories to sys.path here. If the directory is relative to the
# documentation root, use os.path.abspath to make it absolute, like shown here.
#
# import os
# import sys
# sys.path.insert(0, os.path.abspath('.'))


# -- Project information -----------------------------------------------------

project = 'Corvassembly'
copyright = '2021, Corvus Prudens'
author = 'Corvus Prudens'

# The full version, including alpha/beta/rc tags
release = '1.0.0'


# -- General configuration ---------------------------------------------------

# Add any Sphinx extension module names here, as strings. They can be
# extensions coming with Sphinx (named 'sphinx.ext.*') or your custom
# ones.
extensions = [
]

# Add any paths that contain templates here, relative to this directory.
templates_path = ['_templates']

# List of patterns, relative to source directory, that match files and
# directories to ignore when looking for source files.
# This pattern also affects html_static_path and html_extra_path.
exclude_patterns = ['_build', 'Thumbs.db', '.DS_Store']


# -- Options for HTML output -------------------------------------------------

# The theme to use for HTML and HTML Help pages.  See the documentation for
# a list of builtin themes.
#
html_theme = 'sphinx_rtd_theme'

# Add any paths that contain custom static files (such as style sheets) here,
# relative to this directory. They are copied after the builtin static files,
# so a file named "default.css" will overwrite the builtin "default.css".
html_static_path = ['_static']

# -- Options for Lexing ------------------------------------------------------
from pygments.lexer import RegexLexer
from pygments import token
from sphinx.highlighting import lexers

class BCLLexer(RegexLexer):
    name = 'corvassembly'
    aliases = ['cor']

    tokens = {
        'root': [
            (r'/\*', token.Comment, 'comment'),
            (r'//.*', token.Comment),
            (r'\b(for|break|breakall|continue|if|elif|else|is|isnt)\b', token.Keyword),
            (r'\b(carry|zero|negative|equal|greater|less)(?!\w+)', token.Name.Decorator),
            (r'(?<!\w)\b(pre|ram|rom|gpu|ptr|import|as)\b', token.Keyword.Type),
            (r'(?<=,) *(ram|rom|gpu)', token.Keyword),
            (r'\b(([A-z]|_)\w*)(?=:|(\([A-Za-z_][A-Za-z_0-9]*\)))', token.Name.Label),
            (r'\b([a-h])\b', token.Name.Builtin),
            (r'\b(NOP|nop|RTS|rts|LDR|ldr|STR|str|LPT|lpt|SPT|spt|CMP|cmp|ADD|add|SUB|sub|MUL|mul|DIV|div|MOD|mod|AND|and|OR|or|XOR|xor|NOT|not|LSL|lsl|LSR|lsr|PSH|psh|POP|pop|PEK|pek|JMP|jmp|JSR|jsr|JOC|joc|JSC|jsc|RSC|rsc|rti|RTI|ric|RIC)\b', token.Name.Function),
            (r'\b([A-Z_][A-Z0-9_]*)\b', token.Name.Constant),
            (r'[A-Za-z_][A-Za-z_0-9]*', token.Name),
            (r'[=+\-/\*<>\&\|\^~]', token.Operator),
            (r'\s|[\[\]{}:();,.]', token.Text),
            ('"', token.String, 'string'),
            ('\'\\\\?[A-z]\'', token.String.Char),
            (r'\b((0x)|(0b))?([1-9][0-9]*|0)(?!\w+)', token.Number),
            # (r'\s', token.Text)
        ],
        'string': [
            ('[^"]+', token.String),
            ('"', token.String, '#pop'),
        ],
        'comment': [
          ('[^"]+', token.Comment),
          ('\*/', token.Comment, '#pop'),
        ]
    }

lexers['corvassembly'] = BCLLexer(startinline=True)
