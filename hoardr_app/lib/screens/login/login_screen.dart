import 'package:flutter/material.dart';
import 'package:hoardr_app/constants.dart';
import 'package:hoardr_app/screens/login/components/login_form.dart';
import 'package:hoardr_app/size_config.dart';

class LoginScreen extends StatelessWidget {
  const LoginScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: SingleChildScrollView(
          padding:
              EdgeInsets.fromLTRB(24.width, 32.height, 24.width, 29.height),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                "Hello, \nWe are glad to have you back",
                style: TextStyle(
                  fontSize: 25,
                  fontWeight: FontWeight.w700,
                  color: kTextColor,
                ),
              ),
              SizedBox(height: 40.height),
              LoginForm(),
            ],
          ),
        ),
      ),
    );
  }
}
