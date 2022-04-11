import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:frontend_flutter/networks/user_ops.dart';
import 'package:frontend_flutter/screens/home.dart';
import 'package:frontend_flutter/widgets/text_field.dart';

import '../constant/routs.dart';
import '../models/api_res.dart';
import '../models/user.dart';

class RegistrationPage extends StatefulWidget {
  const RegistrationPage({Key? key}) : super(key: key);

  @override
  _RegistrationPageState createState() => _RegistrationPageState();
}

class _RegistrationPageState extends State<RegistrationPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: const SingleChildScrollView(child: RegistrationBody()),
      appBar: AppBar(
        title: const Text(HomePage.title),
      ),
    );
  }
}

class RegistrationBody extends StatefulWidget {
  const RegistrationBody({Key? key}) : super(key: key);

  @override
  _RegistrationBodyState createState() => _RegistrationBodyState();
}

class _RegistrationBodyState extends State<RegistrationBody> {

  final _useridController = TextEditingController();
  final _mobileController = TextEditingController();
  final _passwordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(20.0),
      child: Column(
        children: [
          Image.asset("assets/images/registrationimage.jpg"),
          const Text(
            "Register Here",
            style: TextStyle(
                fontSize: 25,
                fontFamily: "RobotoMono",
                fontWeight: FontWeight.bold,
                color: Colors.blue),
          ),
          TextBox(controller: _useridController, label: "User ID"),
          TextBox(controller: _passwordController, label: "Password"),
          TextBox(controller: _mobileController, label: "Mobile"),
          Column(
            children: [
              ElevatedButton(
                onPressed: () {
                  User user = User(
                      userId: int.parse(_useridController.text.toString()),
                      password: _passwordController.text,
                      mobile: _mobileController.text);

                  print(user);
                  register(user).then((res) {
                    print(res.body);
                    ApiResponse apires =
                        ApiResponse.fromMap(jsonDecode(res.body));

                    SnackBar snackBar = SnackBar(
                      content: Text(apires.message),
                    );

                    ScaffoldMessenger.of(context).showSnackBar(snackBar);
                    if (res.statusCode == 200) {
                  //    Navigator.pushReplacementNamed(context, Routes.home);
                    }
                  });
                },
                child: const Text('Register'),
              ),
              TextButton(
                  onPressed: () {
                    Navigator.pushReplacementNamed(context, Routes.login);
                  },
                  child: Text('Already a user? Sign in here'))
            ],
          )
        ],
      ),
    );
  }
}
