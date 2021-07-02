import 'package:flutter/material.dart';
import 'package:hoardr_app/constants.dart';
import 'package:hoardr_app/size_config.dart';

class CheckboxAndTitle extends StatefulWidget {
  @override
  _CheckboxAndTitleState createState() => _CheckboxAndTitleState();
}

class _CheckboxAndTitleState extends State<CheckboxAndTitle> {
  bool rememberMe = false;
  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: () {
        setState(() {
          rememberMe = !rememberMe;
        });
      },
      child: Row(
        children: [
          Container(
            height: 7.height,
            width: 7.width,
            margin: EdgeInsets.only(
              right: 10.0,
              left: 5.0,
            ),
            child: Checkbox(
              visualDensity: VisualDensity.adaptivePlatformDensity,
                activeColor: kTextColor,
                value: rememberMe,
                onChanged: (value) {
                  setState(() {
                    rememberMe = value!;
                  });
                }),
          ),
          Text(
            "Remember me",
            style: TextStyle(
              color: kTextColor,
              fontWeight: FontWeight.w400,
              fontSize: 13,
            ),
          ),
        ],
      ),
    );
  }
}
