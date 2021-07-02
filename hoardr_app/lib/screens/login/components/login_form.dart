import 'package:flutter/material.dart';
import 'package:hoardr_app/constants.dart';
import 'package:hoardr_app/size_config.dart';
import 'package:hoardr_app/widgets/default_button.dart';

import 'checkbox_and_title.dart';

class LoginForm extends StatefulWidget {
  const LoginForm({Key? key}) : super(key: key);

  @override
  _LoginFormState createState() => _LoginFormState();
}

class _LoginFormState extends State<LoginForm> {
  bool isTyping = false;
  FocusNode _textFieldFocus = FocusNode();
  TextEditingController emailController = TextEditingController();
  FocusNode _passwordFocusNode = FocusNode();
  TextEditingController passwordController = TextEditingController();
  String bullet = "\u2022 ";
  bool passwordError = false;
  bool emailError = false;
  String? errorText;
  String? passwordErrorText;

  void setEmailError(bool error) {
    setState(() {
      emailError = error;
    });
  }

  void setPasswordError(bool error) {
    setState(() {
      passwordError = error;
    });
  }

  @override
  Widget build(BuildContext context) {
    void checkIfFocused(FocusNode? node) {
      node!.addListener(() {
        setState(() {});
      });
    }

    Widget spaceAfter() {
      if (emailError && !passwordError) {
        return SizedBox(height: 76.height);
      } else if (passwordError && !emailError) {
        return SizedBox(height: 26.height);
      } else if (passwordError && emailError) {
        return SizedBox.shrink();
      } else {
        return SizedBox(height: 219.height);
      }
    }

    return Form(
        child: Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        Text(
          "Email address",
          style: TextStyle(fontSize: 16, fontWeight: FontWeight.w400),
        ),
        Padding(
          padding: EdgeInsets.only(top: 12.0.height),
          child: TextFormField(
            controller: emailController,
            onFieldSubmitted: (value) {
              emailController.addListener(() {
                if (!emailValidatorRegExp.hasMatch(value)) {
                  setEmailError(true);
                  errorText =
                      "The email address you entered is wrong, kindly confirm if it follows the email requirements";
                } else {
                  setEmailError(false);
                  errorText = null;
                }
              });
            },
            onChanged: (value) {},
            onTap: () {
              checkIfFocused(_textFieldFocus);
            },
            focusNode: _textFieldFocus,
            decoration: kInputDecoration.copyWith(
              errorText: errorText,
              fillColor:
                  _textFieldFocus.hasFocus ? Color(0xffF7F7F8) : Colors.white,
            ),
          ),
        ),
        SizedBox(height: 18.height),
        EmailError(emailError: emailError, bullet: bullet),
        Text("Password",
            style: TextStyle(fontSize: 16, fontWeight: FontWeight.w400)),
        Padding(
          padding: EdgeInsets.only(top: 12.0.height),
          child: TextFormField(
            obscureText: true,
            controller: passwordController,
            onFieldSubmitted: (value) {
              passwordController.addListener(() {
                if (!atleastOneLowerCase.hasMatch(value) ||
                    !atleastOneUpperCase.hasMatch(value) ||
                    !atleastOneDigit.hasMatch(value) ||
                    !atleastOneSpecial.hasMatch(value)) {
                  setPasswordError(true);
                  passwordErrorText =
                      "The password you entered is wrong, kindly confirm if you missed any of the requirements below.";
                } else {
                  setPasswordError(false);
                  passwordErrorText = null;
                }
              });
            },
            onTap: () {
              checkIfFocused(_passwordFocusNode);
            },
            focusNode: _passwordFocusNode,
            decoration: kInputDecoration.copyWith(
              errorText: passwordErrorText,
              fillColor: _passwordFocusNode.hasFocus
                  ? Color(0xffF7F7F8)
                  : Colors.white,
              hintText: "",
              suffixIcon: IconButton(
                icon: Icon(Icons.visibility),
                color: kTextColor,
                onPressed: () {},
              ),
            ),
          ),
        ),
        SizedBox(height: 16.height),
        Visibility(
          visible: passwordError,
          child: Container(
              child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                Text("Passwords must contain the following elements",
                    style: TextStyle(color: kTextColor2, fontSize: 13)),
                Padding(
                  padding: const EdgeInsets.only(left: 5.0, top: 8.0),
                  child: Text(
                      "$bullet 1 or more lowercase letters \n$bullet 1 or more upercase letters \n$bullet 1 or more numbers \n$bullet 1 or more of the following characters @, \$$bullet , ! \n$bullet Password must be between 8 & 10 characters",
                      style: TextStyle(color: kTextColor2, fontSize: 13)),
                ),
                 SizedBox(height: 34.height),
              ]),),
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Expanded(
              child: CheckboxAndTitle(),
            ),
            TextButton(
                onPressed: () {},
                child: Text(
                  "Forget Password?",
                  style: TextStyle(
                      color: kTextColor,
                      fontSize: 13,
                      fontStyle: FontStyle.italic,
                      fontWeight: FontWeight.w400),
                )),
          ],
        ),
        spaceAfter(),
        DefaultButton(
          child: Text(
            "LOGIN",
            style: TextStyle(color: Colors.white, fontWeight: FontWeight.w600),
          ),
        ),
        SizedBox(height: 25.height),
        TextButton(
          onPressed: () {},
          child: Center(
            child: Text(
              "Don’t have an account? Register today",
              style: TextStyle(
                  color: Colors.black,
                  fontSize: 14.0,
                  fontWeight: FontWeight.w400),
            ),
          ),
        ),
      ],
    ));
  }
}

class EmailError extends StatelessWidget {
  const EmailError({
    Key? key,
    required this.emailError,
    required this.bullet,
  }) : super(key: key);

  final bool emailError;
  final String bullet;

  @override
  Widget build(BuildContext context) {
    return Visibility(
      visible: emailError,
      child: Container(
          child:
              Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
        Text("Email must contain the following elements",
            style: TextStyle(color: kTextColor2, fontSize: 13)),
        Padding(
          padding: const EdgeInsets.only(left: 5.0, top: 8.0),
          child: Text(
              "$bullet Main email variable e.g @gmail.com, @yahoo.com \n$bullet Right names",
              style: TextStyle(color: kTextColor2, fontSize: 13)),
        ),
        SizedBox(height: 24.height),
      ])),
    );
  }
}
