import 'package:flutter/material.dart';
import 'package:hoardr_app/constants.dart';
import 'package:dotted_decoration/dotted_decoration.dart';
import 'package:hoardr_app/screens/addItems_Screens/addItems_Screen2.dart';
import 'package:hoardr_app/screens/addItems_Screens/viewInventory.dart';

class AddItemPage1 extends StatelessWidget {
  const AddItemPage1({Key? key}) : super(key: key);

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
      body: Padding(
        padding: const EdgeInsets.only(
          left: 24,
          top: 18,
          right: 24,
        ),
        child: CustomListTile(context),
      ),
    );
  }
}

Widget CustomListTile(context) {
  return Column(
    children: [
      InkWell(
        onTap: () {
          Navigator.of(context).push(
            MaterialPageRoute(
              builder: (context) => AddItemPage2(),
            ),
          );
        },
        child: Container(
          height: 117,
          padding: EdgeInsets.only(
            left: 18,
            top: 18,
          ),
          decoration: BoxDecoration(
            boxShadow: [
              BoxShadow(color: Colors.black12, blurRadius: 12, spreadRadius: 1),
            ],
            color: Colors.white,
          ),
          alignment: Alignment.topLeft,
          child: Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              CircleAvatar(
                radius: 25,
                backgroundImage: AssetImage('assets/images/uploaditem.png'),
              ),
              SizedBox(
                width: 12,
              ),
              Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Padding(
                    padding: const EdgeInsets.only(
                      top: 8.0,
                      left: 2.0,
                    ),
                    child: Text.rich(
                      TextSpan(
                        text: 'Upload item',
                        style: TextStyle(
                          fontWeight: FontWeight.w700,
                          fontSize: 20,
                          color: kTextColor,
                        ),
                      ),
                    ),
                  ),
                  Text.rich(
                    TextSpan(
                      text: " \n Have a new item, upload by clicking on this "
                          " \n button.",
                      style: TextStyle(
                        fontWeight: FontWeight.normal,
                        fontSize: 13,
                        color: kTextColor,
                      ),
                    ),
                  ),
                ],
              )
            ],
          ),
        ),
      ),
      SizedBox(
        height: 6,
      ),
      InkWell(
        onTap: () async {
          await Navigator.of(context).push(
            MaterialPageRoute(
              builder: (context) => InventoryPage(),
            ),
          );
        },
        child: Container(
          height: 117,
          padding: EdgeInsets.only(
            left: 18,
            top: 18,
          ),
          decoration: DottedDecoration(
            borderRadius: BorderRadius.circular(2),
            shape: Shape.box,
            color: kPrimaryColor,
          ),
          alignment: Alignment.topLeft,
          child: Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              CircleAvatar(
                radius: 25,
                backgroundImage: AssetImage('assets/images/inventory.png'),
              ),
              SizedBox(
                width: 12,
              ),
              Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Padding(
                    padding: const EdgeInsets.only(
                      top: 8.0,
                      left: 2.0,
                    ),
                    child: Text.rich(
                      TextSpan(
                        text: 'View Inventory',
                        style: TextStyle(
                          fontWeight: FontWeight.w700,
                          fontSize: 20,
                          color: kTextColor,
                        ),
                      ),
                    ),
                  ),
                  Text.rich(
                    TextSpan(
                      text: " \n View Items uploaded, check quantity "
                          " \n available and edit them.",
                      style: TextStyle(
                        fontWeight: FontWeight.normal,
                        fontSize: 13,
                        color: kTextColor,
                      ),
                    ),
                  ),
                ],
              )
            ],
          ),
        ),
      ),
    ],
  );
}
