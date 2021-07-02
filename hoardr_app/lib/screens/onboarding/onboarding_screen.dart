import 'package:flutter/material.dart';
import 'package:hoardr_app/constants.dart';
import 'package:hoardr_app/screens/login/login_screen.dart';
import 'package:hoardr_app/screens/registration/registration_screen.dart';
import 'package:hoardr_app/size_config.dart';
import 'package:hoardr_app/widgets/default_button.dart';
import 'component/onboarding_content.dart';

class OnboardingScreen extends StatefulWidget {
  const OnboardingScreen({Key? key}) : super(key: key);

  @override
  _OnboardingScreenState createState() => _OnboardingScreenState();
}

class _OnboardingScreenState extends State<OnboardingScreen> {
  int currentPage = 0;
  @override
  Widget build(BuildContext context) {
    SizeConfig().init(context);
    PageController pageController = PageController(initialPage: 0);
    return Scaffold(
      body: SafeArea(
        child: Container(
          padding: EdgeInsets.fromLTRB(24.width, 19.height, 24.width, 0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Expanded(
                  child: PageView.builder(
                onPageChanged: (value) {
                  setState(() {
                    currentPage = value;
                  });
                },
                itemCount: 3,
                controller: pageController,
                itemBuilder: (context, index) =>
                    OnboardingContent(index: index),
              )),
              SizedBox(
                height: 20.height,
              ),
              Row(
                children: List.generate(
                  3,
                  (index) => buildDot(index: index),
                ),
              ),
              SizedBox(
                height: 32.height,
              ),
              DefaultButton(
                  child: Text(
                    currentPage == 2 ? "DECLUTTER YOUR SPACE TODAY" : "NEXT",
                    style: TextStyle(
                        color: Colors.white, fontWeight: FontWeight.w600),
                  ),
                  onTap: () {
                    if (currentPage < 2) {
                      currentPage = currentPage + 1;
                      pageController.animateToPage(currentPage,
                          duration: kAnimationDuration,
                          curve: Curves.easeInOut);
                    } else {
                        Navigator.of(context).push(MaterialPageRoute(
                        builder: (context) => RegistrationScreen()));
                    }
                  }),
              SizedBox(
                height: 22.height,
              ),
              TextButton(
                onPressed: () {
                  if (currentPage < 2) {
                    Navigator.of(context).push(MaterialPageRoute(
                        builder: (context) => RegistrationScreen()));
                  } else {
                    Navigator.of(context).push(
                        MaterialPageRoute(builder: (context) => LoginScreen()));
                  }
                },
                child: Center(
                  child: Text(
                    currentPage == 2
                        ? "Already have an account? Login"
                        : "Skip >",
                    style: TextStyle(
                        color: Colors.black,
                        fontSize: 14.0,
                        fontWeight: FontWeight.w400),
                  ),
                ),
              ),
              SizedBox(
                height: 9.height,
              )
            ],
          ),
        ),
      ),
    );
  }

  AnimatedContainer buildDot({int? index}) {
    return AnimatedContainer(
      duration: kAnimationDuration,
      margin: EdgeInsets.only(right: 8.width),
      height: 8,
      width: 8,
      decoration: BoxDecoration(
        shape: BoxShape.circle,
        color: currentPage == index
            ? Colors.red
            : Color(0xffF7F7F8), //Color(0xFFD8D8D8)
      ),
    );
  }
}

