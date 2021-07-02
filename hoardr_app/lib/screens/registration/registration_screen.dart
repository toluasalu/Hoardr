import 'package:flutter/material.dart';
import 'package:hoardr_app/constants.dart';
import 'package:hoardr_app/screens/login/login_screen.dart';
import 'package:hoardr_app/size_config.dart';
import 'package:hoardr_app/widgets/default_button.dart';
import 'package:intl_phone_number_input/intl_phone_number_input.dart';

class RegistrationScreen extends StatelessWidget {
  RegistrationScreen({Key? key}) : super(key: key);
  String initialCountry = 'NG';
  PhoneNumber number = PhoneNumber(isoCode: 'NG');
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: SingleChildScrollView(
          padding:
              EdgeInsets.fromLTRB(24.width, 32.height, 24.width, 32.height),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              RichText(
                text: TextSpan(
                  children: [
                    TextSpan(text: "1 / "),
                    TextSpan(text: "3", style: TextStyle(color: kTextColor2)),
                  ],
                  style: TextStyle(
                      color: kTextColor,
                      fontSize: 25,
                      fontWeight: FontWeight.w700),
                ),
              ),
              SizedBox(height: 10.height),
              Text(
                "Help us create a solution that moves the world.",
                style: TextStyle(
                    color: kTextColor,
                    fontSize: 25,
                    fontWeight: FontWeight.w700),
              ),
              Padding(
                padding: EdgeInsets.only(top: 40.0.height),
                child: Row(
                  children: [
                    Expanded(
                      child: CustomTextField(
                        title: "First name",
                        decoration: kInputDecoration,
                      ),
                    ),
                    SizedBox(
                      width: 20.width,
                    ),
                    Expanded(
                      child: CustomTextField(
                        title: "Last name",
                        decoration: kInputDecoration,
                      ),
                    ),
                  ],
                ),
              ),
              SizedBox(height: 18.height),
              CustomTextField(
                  title: "Email Address", decoration: kInputDecoration),
              SizedBox(height: 18.height),
              Text(
                "Phone number",
                style: TextStyle(fontSize: 16, fontWeight: FontWeight.w400),
              ),
              Container(
                padding: EdgeInsets.symmetric(vertical: 8.height),
                margin: EdgeInsets.only(top: 12.height),
                decoration: BoxDecoration(
                  border: Border.all(color: Color(0xff818195)),
                  borderRadius: BorderRadius.circular(5.0),
                ),
                child: InternationalPhoneNumberInput(
                  onInputChanged: (PhoneNumber number) {
                    print(number.phoneNumber);
                  },
                  onInputValidated: (bool value) {
                    print(value);
                  },
                  selectorConfig: SelectorConfig(
                    selectorType: PhoneInputSelectorType.DROPDOWN,
                  ),
                  autoValidateMode: AutovalidateMode.disabled,
                  selectorTextStyle: TextStyle(color: Colors.black),
                  initialValue: number,
                  //textFieldController: controller,
                  formatInput: false,
                  keyboardType: TextInputType.numberWithOptions(
                      signed: true, decimal: true),
                  inputBorder: InputBorder.none,
                  onSaved: (PhoneNumber number) {
                    print('On Saved: $number');
                  },
                ),
              ),
              SizedBox(height: 110.height),
              DefaultButton(
                  child: Text(
                    "NEXT",
                    style: TextStyle(
                        color: Colors.white, fontWeight: FontWeight.w600),
                  ),
                  onTap: () {}),
              SizedBox(
                height: 22.height,
              ),
              TextButton(
                onPressed: () {
                  Navigator.of(context).push(
                      MaterialPageRoute(builder: (context) => LoginScreen()));
                },
                child: Center(
                  child: Text(
                    "Already have an account? Login",
                    style: TextStyle(
                        color: Colors.black,
                        fontSize: 14.0,
                        fontWeight: FontWeight.w400),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class CustomTextField extends StatelessWidget {
  CustomTextField({
    Key? key,
    this.title,
    this.controller,
    this.decoration,
  }) : super(key: key);
  final String? title;
  final TextEditingController? controller;
  final InputDecoration? decoration;
  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(
          title!,
          style: TextStyle(fontSize: 16, fontWeight: FontWeight.w400),
        ),
        Padding(
          padding: EdgeInsets.only(top: 12.0.height),
          child: TextFormField(
            controller: controller,
            decoration: decoration,
          ),
        ),
      ],
    );
  }
}
