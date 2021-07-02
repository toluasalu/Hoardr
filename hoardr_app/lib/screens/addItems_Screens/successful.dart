import 'package:flutter/material.dart';
import 'package:hoardr_app/constants.dart';
import 'package:hoardr_app/screens/addItems_Screens/addItem_1.dart';

class UploadSuccess extends StatelessWidget {
  const UploadSuccess({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SingleChildScrollView(
        padding: EdgeInsets.only(top: 203, right: 24, left: 24),
        child: Column(
          children: [
            Container(
              height: 232,
              width: 232,
              decoration: BoxDecoration(
                image: DecorationImage(
                  image: AssetImage('assets/images/cloud.png'),
                ),
              ),
            ),
            SizedBox(
              height: 40,
            ),
            Text(
              'Item successfully uploaded',
              style: TextStyle(fontSize: 16, fontWeight: FontWeight.w400),
            ),
            SizedBox(
              height: 200,
            ),
            Container(
              width: double.infinity,
              height: 58,
              decoration: BoxDecoration(
                color: kPrimaryColor,
              ),
              child: TextButton(
                onPressed: () {
                  Navigator.of(context).pop();
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
            ),
          ],
        ),
      ),
    );
  }
}
