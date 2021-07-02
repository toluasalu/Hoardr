import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:hoardr_app/screens/onboarding/onboarding_screen.dart';

void main() {
  runApp(HoardrApp());
}

class HoardrApp extends StatelessWidget {
  const HoardrApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        textTheme: GoogleFonts.mulishTextTheme(
          Theme.of(context).textTheme,
        ),
      ),
      home: OnboardingScreen(),
    );
  }
}
