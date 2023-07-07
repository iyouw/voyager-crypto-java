# org.linkerdesign.crypto

A lightweight crypto library which could run on `browser`, `node`, `java(jvm)`, `dotnet` platform base on `openssl libcrypto library`.

* `browser`, `node` package
	+ we use `webassembly` technology to port and encapsulate the `native c library`(`openssl libcrypto`). you could access the package [here](https://www.npmjs.com/package/@linker-design/crypto)

* `dotnet` package
	+ we use `p/invoke` technology to port and encapsulate the `native c library`(`openssl libcrypto`) . you could access the package [here](https://www.nuget.org/packages/LinkerDesign.Crypto)

* `java(jvm)` package
  + we use `jni(java native interface)` technology to port and encapsulate the `native c library`(`openssl libcrypto`). we will publish the package soon!

## Features

1. Symmetric Encryt Decrypt
  + AES-CBC
  + AES-CTR
2. Message Digest
  + Sha1
  + Sha256
  + Sha384
  + Sha512
  + Md5
  + Md5Sha1

more features will come soon!

## usages

1. AES-CBC

We can use AES-CBC to encryt a string who's encoding is utf8 to a base64 string.

Additional, we can AES-CBC to decrypt a base64 string to it's original utf8 string 

```java
import static java.lang.System.out;

import java.util.Base64;
import java.util.Arrays;
import java.io.UnsupportedEncodingException;

import org.linkerdesign.crypto.abstraction.AesKeySize;
import org.linkerdesign.crypto.abstraction.ExportType;
import org.linkerdesign.crypto.symmetricencryptdecrypt.Aes;

import org.linkerdesign.crypto.abstraction.ExportType;

import java.io.UnsupportedEncodingException;

public class App {
	public static final String MSG = "《青溪》是唐代诗人王维创作的一首五言古诗。此诗描写了一条青溪的幽秀景色，诗人用多彩的画笔，绘出青溪流经不同地方时呈现的不同画面。其中“声喧乱石中，色静深松里”两句，以喧响的声音和幽冷的色调形成闹与静的强烈对比，如同一幅“有声画”。诗的末四句写出诗人心境的闲谈正如清川的闲淡，把自己的精神和自然的精神融和起来，意味隽永。全诗自然清淡素雅，写景抒情皆轻轻松松，然而韵味却隽永醇厚。诗人笔下的青溪是喧闹与沉郁的统一，活泼与安详的揉合，幽深与素静的融和。";

	public static void main(String[] args) throws UnsupportedEncodingException {
		testAes();
	}

	static void testAes() throws UnsupportedEncodingException {
		var aes = new Aes();

    var key = aes.generateKey(AesKeySize.KS256, ExportType.Base64);
    var iv = aes.generateIV(ExportType.Base64);

    var enc = aes.encryptCBCWithUTF8(key, iv, MSG, ExportType.Base64);
    var dec = aes.decryptCBCWithUTF8(key, iv, enc, ExportType.Base64);

		out.println(String.format("%s:::equals: %b", dec, MSG.equals(dec)));
	}
}
```

2. Message Digest

support regular message digest algorith, like `sha1`, `sha256`, `md5` etc...

```java
import static java.lang.System.out;

import java.util.Base64;
import java.util.Arrays;
import java.io.UnsupportedEncodingException;

import org.linkerdesign.crypto.abstraction.ExportType;

import org.linkerdesign.crypto.abstraction.ExportType;
import org.linkerdesign.crypto.messagedigest.Md5;
import org.linkerdesign.crypto.messagedigest.Md5Sha1;
import org.linkerdesign.crypto.messagedigest.Sha1;
import org.linkerdesign.crypto.messagedigest.Sha256;
import org.linkerdesign.crypto.messagedigest.Sha384;
import org.linkerdesign.crypto.messagedigest.Sha512;

import java.io.UnsupportedEncodingException;

public class App {
	public static final String MSG = "《青溪》是唐代诗人王维创作的一首五言古诗。此诗描写了一条青溪的幽秀景色，诗人用多彩的画笔，绘出青溪流经不同地方时呈现的不同画面。其中“声喧乱石中，色静深松里”两句，以喧响的声音和幽冷的色调形成闹与静的强烈对比，如同一幅“有声画”。诗的末四句写出诗人心境的闲谈正如清川的闲淡，把自己的精神和自然的精神融和起来，意味隽永。全诗自然清淡素雅，写景抒情皆轻轻松松，然而韵味却隽永醇厚。诗人笔下的青溪是喧闹与沉郁的统一，活泼与安详的揉合，幽深与素静的融和。";

	public static void main(String[] args) throws UnsupportedEncodingException {
		testMd();
	}

	static void testMd() throws UnsupportedEncodingException {
		var sha1 = new Sha1();
		var hash = sha1.digest(MSG, ExportType.UTF8, ExportType.Base64);
		out.println(String.format("%s:::%d", hash, hash.length()) );

		var sha256 = new Sha256();
		hash = sha256.digest(MSG, ExportType.UTF8, ExportType.Hex);
		out.println(String.format("%s:::%d", hash, hash.length()) );

		var sha384 = new Sha384();
		hash = sha384.digest(MSG, ExportType.UTF8, ExportType.Hex);
		out.println(String.format("%s:::%d", hash, hash.length()) );

		var sha512 = new Sha512();
		hash = sha512.digest(MSG, ExportType.UTF8, ExportType.Hex);
		out.println(String.format("%s:::%d", hash, hash.length()) );

		var md5 = new Md5();
		var hashWeb = "51dac0da1863951d696e027892bdc177";
		hash = md5.digest(MSG, ExportType.UTF8, ExportType.Hex);

		out.println(String.format("%s:::%d:::equals: %b", hash, hash.length(), hashWeb.equals(hash)));

		var md5Sha1 = new Md5Sha1();
		hash = md5Sha1.digest(MSG, ExportType.UTF8, ExportType.Hex);
		out.println(String.format("%s:::%d", hash, hash.length()) );
	}
}
```