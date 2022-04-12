import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:frontend_flutter/local_storage/localops.dart';

import '../constant/routs.dart';
import '../models/api_res.dart';
import '../models/user.dart';

class WelcomePage extends StatefulWidget {
  const WelcomePage({Key? key}) : super(key: key);

  @override
  _WelcomePageState createState() => _WelcomePageState();
}

class _WelcomePageState extends State<WelcomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: const SingleChildScrollView(child: WelcomeBody()),
      appBar: AppBar(
        title:   Text(Routes.appname),
      ),
    );
  }
}

class WelcomeBody extends StatefulWidget {
  const WelcomeBody({Key? key}) : super(key: key);

  @override
  _WelcomeBodyState createState() => _WelcomeBodyState();
}

class _WelcomeBodyState extends State<WelcomeBody> {


  @override
  Widget build(BuildContext context) {

    return Padding(
      padding: const EdgeInsets.all(20.0),
      child: Column(
        children: [
          Image.asset("assets/images/registrationimage.jpg"),
          const Text(
            "Welcome",
            style: TextStyle(
                fontSize: 25,
                fontFamily: "RobotoMono",
                fontWeight: FontWeight.bold,
                color: Colors.blue),
          ),

          Column(
            children: [
              ElevatedButton(
                onPressed: () {
                  clearStorage();
                  SnackBar snackBar = SnackBar(
                    content: Text('Logged out successfuly !'),
                  );
                  ScaffoldMessenger.of(context).showSnackBar(snackBar);
                  Navigator.pushReplacementNamed(context, Routes.login);

                },
                child: const Text('Logout'),
              ),

            ],
          )
        ],
      ),
    );
  }


}
