import 'package:flutter/material.dart';

class SizeConfig {
  static MediaQueryData? _mediaQueryData;

  ///width of the screen its being projected on
  static double? screenWidth;

  ///height of the screen its being projected on
  static double? screenHeight;

  void init(BuildContext context) {
    _mediaQueryData = MediaQuery.of(context);
    screenWidth = _mediaQueryData!.size.width;
    screenHeight = _mediaQueryData!.size.height;
  }
}

extension CustomSize on num {
  ///get the proportionate height compared to the one used in the design 
  double get height => () {
        double screenHeight = SizeConfig.screenHeight!;
        return (this / 823.0) * screenHeight;
      }();
  ///get the proportionate width compared to the one used in the design 
  double get width => () {
        double screenWidth = SizeConfig.screenWidth!;
        return (this / 411.0) * screenWidth;
      }();
}
