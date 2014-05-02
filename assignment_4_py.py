import markdown

text="*Hey wats up?*"
infile=".\\assignment_4_txt.txt"
outfile=".\\assignment_4_html.html"
html=markdown.markdownFromFile(infile,outfile)
print html
