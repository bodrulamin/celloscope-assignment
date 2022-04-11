import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:frontend_flutter/networks/user_ops.dart';
import 'package:frontend_flutter/screens/home.dart';
import 'package:frontend_flutter/widgets/text_field.dart';

import '../constant/routs.dart';
import '../local_storage/localops.dart';
import '../models/api_res.dart';
import '../models/user.dart';

class ForgotPage extends StatefulWidget {
  const ForgotPage({Key? key}) : super(key: key);

  @override
  _ForgotPageState createState() => _ForgotPageState();
}

class _ForgotPageState extends State<ForgotPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: const SingleChildScrollView(child: LoginBody()),
      appBar: AppBar(
        title: const Text(HomePage.title),
      ),
    );
  }
}

class LoginBody extends StatefulWidget {
  const LoginBody({Key? key}) : super(key: key);

  @override
  _LoginBodyState createState() => _LoginBodyState();
}

class _LoginBodyState extends State<LoginBody> {
  bool infoMatched = false;

  final _useridController = TextEditingController();
  final _mobileController = TextEditingController();
  final _passwordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        //  Image.asset("assets/images/loginimage.jpg"),
        const Text(
          "Forgot Password",
          style: TextStyle(
              fontSize: 25,
              fontFamily: "RobotoMono",
              fontWeight: FontWeight.bold,
              color: Colors.blue),
        ),
        TextBox(
            controller: _useridController,
            label: "User ID",
            disabled: infoMatched),
        TextBox(
          controller: _mobileController,
          label: "Mobile",
        ),
        if (infoMatched)
          TextBox(
            controller: _passwordController,
            label: "Password",
          ),
        Column(
          children: [
            ElevatedButton(
              onPressed: () {
                User user = User(
                  userId: int.parse(_useridController.text),
                  mobile: _mobileController.text,
                  password: infoMatched ? _passwordController.text : "",
                );
                if (!infoMatched) {
                  forgot(user).then((res) {
                    ApiResponse apires =
                        ApiResponse.fromMap(jsonDecode(res.body));

                    SnackBar snackBar = SnackBar(
                      content: Text(apires.message),
                    );
                    ScaffoldMessenger.of(context).showSnackBar(snackBar);
                    if (res.statusCode == 200) {
                      infoMatched = true;
                      setState(() {});
                      User loggedInUser = User.fromMap(apires.data['user']);

                      // Navigator.pushReplacementNamed(context, Routes.home);
                    }
                  });
                }else {
                  updateUser(user).then((res) {
                    print(res.body);
                    ApiResponse apires =
                    ApiResponse.fromMap(jsonDecode(res.body));

                    SnackBar snackBar = SnackBar(
                      content: Text(apires.message),
                    );

                    ScaffoldMessenger.of(context).showSnackBar(snackBar);
                    if (res.statusCode == 200) {
                         Navigator.pushReplacementNamed(context, Routes.login);
                    }
                  });
                }
              },
              child: Text(infoMatched ? 'Update' : 'Check'),
            ),
            TextButton(
                onPressed: () {
                  Navigator.pushReplacementNamed(context, Routes.login);
                },
                child: const Text('Remembered password? Login here'))
          ],
        )
      ],
    );
  }
}
