# Financial Report Parser
A dummy project to extract data from financial reports of companies; then aggregate them into 1 result file.
# How to use
List all the annual reports of each companies in `.xlsx` format under `financial_data/` folder.<br>
Each files mentioned above have to follow pattern which is `XXX_MMyyyy.xslx` (XXX is the stock symbol of company, MMyyyy is the time period of that report). <br>
Use 2 methods from `ParseManager` class to get the result, which you have to manually add `Company` instances to the list inside its constructor.<br>
I've include some company reports to test.<br>
## p.s
*This is just a basic project for me not to type data cell by cell got from Google (almost 1000 cells with 9-digit-numbers each, suck!). I know the code so suck, and I can't understand it even after 2 days 😂*.



