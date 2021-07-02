import 'package:flutter/material.dart';

const Color kTextColor = Color(0xff4B4B58);
const Color kTextColor2 = Color(0xffA5A5B3);
const Color kPrimaryColor = Color(0xffFF3D57);
const kAnimationDuration = Duration(milliseconds: 300);
final RegExp emailValidatorRegExp =
    RegExp(r"^[a-zA-Z0-9.]+@[a-zA-Z0-9]+\.[a-zA-Z]+");
final RegExp atleastOneLowerCase = RegExp(r"^(?=.*[a-z])");
final RegExp atleastOneUpperCase = RegExp(r"^(?=.*[A-Z])");
final RegExp atleastOneDigit = RegExp(r"^(?=.*?[0-9])");
final RegExp atleastOneSpecial = RegExp(r"^(?=.*?[!@#\$&*~])");
const kInputDecoration = InputDecoration(
  errorMaxLines: 3,
  // filled: true,
  // fillColor: Colors.white,
  hintText: 'abc@example.com',
  enabledBorder: OutlineInputBorder(
    borderSide: BorderSide(
      color: Color(0xff818195),
    ),
  ),
  focusedBorder: OutlineInputBorder(
    borderSide: BorderSide(
      color: Color(0xff818195),
    ),
  ),
  focusedErrorBorder: OutlineInputBorder(
    borderSide: BorderSide(
      color: Colors.red,
    ),
  ),
  errorBorder: OutlineInputBorder(
    borderSide: BorderSide(
      color: Colors.red,
    ),
  ),
);
