import 'package:flutter/material.dart';
import 'package:hoardr_app/constants.dart';
import 'package:hoardr_app/screens/addItems_Screens/additems_screen3.dart';

class AddItemPage2 extends StatefulWidget {
  const AddItemPage2({Key? key}) : super(key: key);

  @override
  AddItemPage2State createState() => AddItemPage2State();
}

class AddItemPage2State extends State<AddItemPage2> {
  List<String> category = [
    "Furniture",
    'jewelry',
    'Electronics',
    'Clothes',
    'Machinery',
    'Accessories',
    'Phones and Tablets',
    'Laptops'
  ];
  String? categories, locations, titles, describeItem;

  var _formKey = GlobalKey<FormState>();
  String? itemChoose;
  AddItemPage2State(
      {this.itemChoose, this.describeItem, this.locations, this.titles});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: kPrimaryColor,
        title: Text(
          "Add item",
        ),
        centerTitle: true,
      ),
      body: SingleChildScrollView(
        child: Padding(
          padding:
              const EdgeInsets.only(left: 24, top: 16, right: 24, bottom: 25),
          child: Form(
            key: _formKey,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  "Make your Items visible." " " "1/2",
                  style: TextStyle(
                    fontWeight: FontWeight.w700,
                    fontSize: 16,
                  ),
                ),
                SizedBox(
                  height: 30,
                ),
                textMethod(title: "Category*"),
                SizedBox(
                  height: 12,
                ),
                Container(
                  height: 56,
                  padding: EdgeInsets.only(
                    left: 12,
                    right: 12,
                    top: 2,
                  ),
                  decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(5),
                      border: Border.all(
                        color: kTextColor,
                      )),
                  child: DropdownButton(
                    underline: SizedBox(),
                    style: TextStyle(
                      color: kTextColor,
                      fontSize: 16,
                    ),
                    isExpanded: true,
                    iconSize: 30,
                    icon: Icon(Icons.keyboard_arrow_down),
                    hint: Text('Select Category'),
                    items: category.map((valueItem) {
                      return DropdownMenuItem(
                        value: valueItem,
                        child: Text(valueItem),
                      );
                    }).toList(),
                    value: itemChoose,
                    onChanged: (String? newValue) {
                      setState(() {
                        itemChoose = newValue;
                      });
                    },
                  ),
                ),
                SizedBox(
                  height: 30,
                ),
                textMethod(title: "Location*"),
                SizedBox(
                  height: 12,
                ),
                TextFormField(
                  validator: (String? value) {
                    if (value!.isEmpty) {
                      return "Please enter a Valid Location";
                    } else {
                      return null;
                    }
                  },
                  onSaved: (String? location) {
                    locations = location;
                    print(locations);
                  },
                  keyboardType: TextInputType.multiline,
                  decoration: InputDecoration(
                    hintText: 'Select Location',
                    border: OutlineInputBorder(
                      borderRadius: BorderRadius.circular(5),
                    ),
                  ),
                ),
                SizedBox(
                  height: 30,
                ),
                textMethod(title: "Title*"),
                SizedBox(
                  height: 12,
                ),
                TextFormField(
                  validator: (String? value) {
                    if (value!.isEmpty) {
                      return "Please enter a valid title";
                    } else {
                      return null;
                    }
                  },
                  onSaved: (String? title) {
                    titles = title;
                  },
                  keyboardType: TextInputType.multiline,
                  decoration: InputDecoration(
                    hintText: 'Enter title',
                    border: OutlineInputBorder(
                      borderRadius: BorderRadius.circular(5),
                    ),
                  ),
                ),
                SizedBox(
                  height: 30,
                ),
                textMethod(title: "Description*"),
                SizedBox(
                  height: 12,
                ),
                Container(
                  padding: EdgeInsets.all(16),
                  height: 125,
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(5),
                    border: Border.all(
                      color: kTextColor,
                    ),
                  ),
                  child: TextFormField(
                    validator: (String? value) {
                      if (value!.isEmpty) {
                        return "Please enter a description!";
                      } else {
                        return null;
                      }
                    },
                    onSaved: (String? description) {
                      describeItem = description;
                    },
                    keyboardType: TextInputType.multiline,
                    maxLines: null,
                    decoration: InputDecoration(
                      hintText: "Describe item",
                      border: InputBorder.none,
                    ),
                  ),
                ),
                SizedBox(
                  height: 35,
                ),
                Container(
                  width: double.infinity,
                  height: 58,
                  decoration: BoxDecoration(
                    color: kPrimaryColor,
                  ),
                  child: TextButton(
                    onPressed: () {
                      if (_formKey.currentState!.validate()) {
                        Navigator.of(context).push(
                          MaterialPageRoute(
                            builder: (context) => AddItemPage3(),
                          ),
                        );
                      } else {
                        return null;
                      }
                    },
                    child: Text(
                      'CONTINUE',
                      style: TextStyle(
                        color: Colors.white,
                        fontWeight: FontWeight.w600,
                        fontSize: 14.54,
                        letterSpacing: 1.3,
                      ),
                    ),
                  ),
                )
              ],
            ),
          ),
        ),
      ),
    );
  }

  Text textMethod({
    required String title,
  }) {
    return Text(
      title,
      style: TextStyle(
        fontWeight: FontWeight.w400,
        fontSize: 16,
      ),
    );
  }
}
