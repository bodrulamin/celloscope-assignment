
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:frontend_flutter/screens/forgot.dart';
 import 'package:frontend_flutter/screens/login.dart';
import 'package:frontend_flutter/screens/registration.dart';
import 'package:frontend_flutter/screens/welcome.dart';
import 'package:frontend_flutter/theme/colors.dart';

import 'constant/routs.dart';
import 'local_storage/localops.dart';
import 'models/user.dart';

void main() {

  runApp(MaterialApp(
    initialRoute: Routes.root,
    routes: {
      Routes.root: (context) =>  LoginWrapper(),
      Routes.login: (context) =>  LoginPage(),
      Routes.registration: (context) =>  RegistrationPage(),
        Routes.forgot: (context) => const ForgotPage(),
       Routes.welcome: (context) => const WelcomePage(),
    },
    theme: ThemeData(
      primarySwatch: colorSwatch,
      visualDensity: VisualDensity.adaptivePlatformDensity,
    ),
    debugShowCheckedModeBanner: false,
  ));
}

class LoginWrapper extends StatefulWidget {
  const LoginWrapper({Key? key}) : super(key: key);

  @override
  _LoginWrapperState createState() => _LoginWrapperState();
}

class _LoginWrapperState extends State<LoginWrapper> {
  User? user = User(userId: 0,mobile: "0",password: "0");

  @override
  void initState() {
    super.initState();
  }

  _clearStorage() async {
    await storage.clear();

    setState(() {
      user = storage.getItem('user') ?? "{}";
    });
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
      future: storage.ready,
      builder: (BuildContext context, snapshot) {
        if (snapshot.data == true) {
          String? userString = storage.getItem("user");

          if (userString == null) {
            //    user = User.fromMap(jsonDecode(userString!)) ;

            return const LoginPage();
          }

          return WelcomePage();
        } else {
          return const CircularProgressIndicator();
        }
      },
    );
  }
}
