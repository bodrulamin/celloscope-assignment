import 'dart:convert';
import 'package:motion_toast/motion_toast.dart';
import 'package:flutter/material.dart';
import 'package:frontend_flutter/networks/user_ops.dart';
import 'package:frontend_flutter/widgets/text_field.dart';

import '../constant/routs.dart';
import '../local_storage/localops.dart';
import '../models/api_res.dart';
import '../models/user.dart';


class LoginPage extends StatefulWidget {
  const LoginPage({Key? key}) : super(key: key);

  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: const SingleChildScrollView(child: LoginBody()),
      appBar: AppBar(
        title:   Text(Routes.appname),
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
  final _formKey = GlobalKey<FormState>();

  final _useridController = TextEditingController();
  final _passwordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Form(
      key: _formKey,
      autovalidateMode: AutovalidateMode.onUserInteraction,
      child: Column(
        children: [
          Image.asset("assets/images/loginimage.jpg"),
          const Text(
            "Login",
            style: TextStyle(
                fontSize: 25,
                fontFamily: "RobotoMono",
                fontWeight: FontWeight.bold,
                color: Colors.blue),
          ),
          TextBox(controller: _useridController, label: "User ID"),
          TextBox(controller: _passwordController, label: "Password"),
          TextButton(
              onPressed: () {
                Navigator.pushReplacementNamed(context, Routes.forgot);
              },
              child: const Text('Forgot password')),
          Column(
            children: [
              ElevatedButton(
                onPressed: () {

                  if (_formKey.currentState!.validate()) {
                    submitRequest(context);
                  }
                },
                child: const Text('Login'),
              ),
              TextButton(
                  onPressed: () {
                    Navigator.pushReplacementNamed(
                        context, Routes.registration);
                  },
                  child: const Text('New user? Sign Up here'))
            ],
          )
        ],
      ),
    );
  }

  void submitRequest(BuildContext context) {
    User user = User(
      userId: int.parse(_useridController.text),
      password: _passwordController.text,
      mobile: '',
    );

    login(user).then((res) {
      ApiResponse apires = ApiResponse.fromMap(jsonDecode(res.body));

      SnackBar snackBar = SnackBar(
        content: Text(apires.message),
      );
      ScaffoldMessenger.of(context).showSnackBar(snackBar);

      if (res.statusCode == 200) {

        User loggedInUser = User.fromMap(apires.data['data']);
        saveToStorage(loggedInUser);
        Navigator.pushReplacementNamed(context, Routes.welcome);
      }
    });
  }
}
