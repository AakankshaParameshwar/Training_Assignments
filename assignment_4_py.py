import markdown

text="*Hey wats up?*"
infile=".\\assignment_ans.txt"
outfile=".\\assignment_ans.html"
html=markdown.markdownFromFile(infile,outfile)
print html
