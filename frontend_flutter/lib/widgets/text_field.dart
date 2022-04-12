import 'package:flutter/material.dart';

import '../theme/colors.dart';

class TextBox extends StatelessWidget {
  const TextBox({
    Key? key,
    required TextEditingController controller,
    required this.label, this.disabled = false,
  })  : _usernameController = controller,
        super(key: key);

  final TextEditingController _usernameController;
  final String label;
  final bool disabled;


  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: TextFormField(
        obscureText: label.toLowerCase() == 'Password'.toLowerCase(),
        controller: _usernameController,
        readOnly: disabled,
        decoration: InputDecoration(
          contentPadding:
              const EdgeInsets.only(left: 20.0, bottom: 8.0, top: 8.0),
          enabledBorder: UnderlineInputBorder(
            borderSide: BorderSide(color: Colors.white),
            borderRadius: BorderRadius.circular(25.7),
          ),
          focusedBorder: UnderlineInputBorder(
            borderSide: BorderSide(color: Colors.white),
            borderRadius: BorderRadius.circular(25.7),
          ),
          disabledBorder: UnderlineInputBorder(
            borderSide: BorderSide(color: Colors.white),
            borderRadius: BorderRadius.circular(25.7),
          ),
          filled: true,
          fillColor: colorSwatch.shade50,
          border: InputBorder.none,
          labelText: label,
        ),
        validator: (value) {
          if (value == null || value.isEmpty) {
            return 'Please enter valid ' + label;
          }
          return null;



        },

      ),
    );
  }
}
