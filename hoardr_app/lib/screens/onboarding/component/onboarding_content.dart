import 'package:flutter/material.dart';
import 'package:hoardr_app/constants.dart';
import 'package:hoardr_app/size_config.dart';

class OnboardingContent extends StatelessWidget {
  OnboardingContent({Key? key, this.index}) : super(key: key);
  final int? index;

  final List<String> images = ['box.png', 'present.png', 'auction.png'];
  final List<String> headlines = [
    "You clutter your space, we make it even better.",
    "Exchange sentiments for better.",
    "Place a bid on your old \nitems"
  ];
  final List<String> body = [
    "Your place doesn’t need to look like a Nigeria cementary before making it look liveable, connect with the right audience today.",
    "Let us help you meet your needs, You can exchange that item you hold sentiments towards for a even better item.",
    "That precious item can give you an even better earning than you have imagined, get onboard today and start earning."
  ];
  @override
  Widget build(BuildContext context) {
    return Container(
      
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Center(
            child: Container(
              height: 325.height,
              width: 314.width,
              child: Image.asset(
                'assets/images/${images[index!]}',
              ),
            ),
          ),
          SizedBox(height: 62.height),
          Text(
            headlines[index!],
            style: TextStyle(
              fontSize: 25,
              fontWeight: FontWeight.w700,
              color: kTextColor,
            ),
          ),
          SizedBox(
            height: 20.height,
          ),
          Text(
            body[index!],
            style: TextStyle(                
                fontSize: 16,
                fontWeight: FontWeight.w400,
                color: Color(0xffA5A5B3)),
          ),
        ],
      ),
    );
  }
}
