import 'package:flutter/material.dart';
import 'package:hoardr_app/constants.dart';
import 'package:hoardr_app/size_config.dart';

class DefaultButton extends StatelessWidget {
  const DefaultButton({
    Key? key,
    required this.child,
    this.onTap,
  }) : super(key: key);
  final Widget? child;
  final VoidCallback? onTap;
  @override
  Widget build(BuildContext context) {
    return InkWell(
      child: Container(
        color: kPrimaryColor,
        padding: EdgeInsets.symmetric(vertical: 20.height),
        child: Center(
          child: child,
        ),
      ),
      onTap: onTap,
    );
  }
}
