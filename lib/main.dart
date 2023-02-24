import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/services.dart';
import 'dart:ui' as ui;

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      title: 'Flutter Demo',
      home: PrintPage(),
    );
  }
}

class PrintPage extends StatelessWidget {
  static const platform = MethodChannel('com.example.pos_flutter/pos');

  const PrintPage({super.key});

  @override
  Widget build(BuildContext context) {
    final globalKey = GlobalKey();
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Container(
                key: globalKey,
                child: const RepaintBoundary(
                  child: Text('print this area'),
                )),
            MaterialButton(
              onPressed: () async => await _print(globalKey),
              child: const Text('PRINT'),
            ),
            MaterialButton(
              onPressed: () async => await _pay(),
              child: const Text('PAY'),
            ),
          ],
        ),
      ),
    );
  }

  Future<void> _print(key) async {
    try {
      var bitMap = await _capture(key);
      var result =
          await platform.invokeMethod('printBitmap', {'bitmapByteArray': bitMap});
      if (kDebugMode) {
        print(result);
      }
    } on PlatformException catch (e) {
      if (kDebugMode) {
        print("Print failed: '${e.message}'.");
      }
    }
  }

  Future<void> _pay()async{
        await platform.invokeMethod('payment', {'amount': "2000"});
  }

  Future<Uint8List?> _capture(key) async {
    try {
      var renderObject =
          key.currentContext!.findRenderObject() as RenderRepaintBoundary;
      RenderRepaintBoundary boundary = renderObject;
      ui.Image captureImage = await boundary.toImage();

      ByteData? byteData =
          await captureImage.toByteData(format: ui.ImageByteFormat.png);
      var bytes = byteData!.buffer.asUint8List();
      return bytes;
    } catch (e) {
      return null;
    }
  }
}
