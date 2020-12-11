Bigfoot! The algorithm that uncovers your carbon footprint on the web.

Check out our video on YouTube: https://youtu.be/Lz8lJO79wuM


Bigfoot is an algorithm that estimates the carbon footprint of a product online.
In order to estimate a product, there are certain attributes that are needed:

The product name,
The product weight in kg,
The product manufacturer name,
The product price in USD.

For the material composition, you need to provide the different materials that make up the product, with percentages for each material.
So if a product was made of half plastic and half metal, the materials would be Plastic 0.5 Metal 0.5.
The material composition of products is pretty hard to find, so for most of our products we estimated the values.
One important tip: there is a material called Electronics, which represents circuit boards and electronic components.
Its good to include this in any electronic device, because electronic components and devices are highly damaging to the environment.

For shipping, all you need to enter is the total distance traveled in km. This is actually pretty hard to find for most products, because
most products are shipped in multiple trips, and held at various warehouses.
We tried to find out where the product was manufactured, and from that we estimated the distance from that.


With the required information, you can now put your product into the algorithm!
There are two different ways to interface with the program, by inputting the values for a product, or with an input table.


To input the values for a product, run the Main class with no parameters:

    java Main


The program should then begin prompting you on the different attributes that you gathered.
Enter in all of the values, all of the units should be stated but make sure to use kg and km.

When entering the material composition, the program will keep prompting you for a material until the total fraction you have given is 1.
For a list of all possible materials, you can type help when it asks for a material.

When you are done entering the data, it should print out the total emmissions, with a breakdown of what contributed.
The results are in kg of CO2 equivalents, which means that the emmissions from this product have a warming potential of that many kg CO2.
This is because some greenhouse gasses have higher warming potentials, so this is a way to account for all emmissions.


To use the input table method, you will need to create a csv table with the product information.
The file products.csv is an example of how it should be formatted, but basically the order of the information is this:

Name, Weight, Seller, Price, Shipping distance, Material 1, Material 1 fraction ... Material N, Material N fraction

With the product table made, run the Main class with the table as a parameter:

    java Main products.csv


The program will print out a breakdown of every products emissions, and create a new table called <tablename>-results.csv with these emmissions.


You can also specify the output table by giving a second parameter:

    java Main products.csv output.csv
