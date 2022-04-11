import 'package:flutter/material.dart';

import '../theme/colors.dart';

class HomePage extends StatefulWidget {
  static const title = 'Celloscope-Assignment';

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {


  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: HomePage.title,
      theme: ThemeData(
        primarySwatch: colorSwatch,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: Scaffold(
        appBar: AppBar(
          title: Text(HomePage.title),
        ),
        body: HomePage(),

      ),
    );
  }


 }
