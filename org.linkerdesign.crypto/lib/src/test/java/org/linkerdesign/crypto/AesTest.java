package org.linkerdesign.crypto;

import org.junit.jupiter.api.Test;
import org.linkerdesign.crypto.abstraction.AesKeySize;
import org.linkerdesign.crypto.abstraction.ExportType;
import org.linkerdesign.crypto.symmetricencryptdecrypt.Aes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;

public class AesTest {
  public final String msg = "《青溪》是唐代诗人王维创作的一首五言古诗。此诗描写了一条青溪的幽秀景色，诗人用多彩的画笔，绘出青溪流经不同地方时呈现的不同画面。其中“声喧乱石中，色静深松里”两句，以喧响的声音和幽冷的色调形成闹与静的强烈对比，如同一幅“有声画”。诗的末四句写出诗人心境的闲谈正如清川的闲淡，把自己的精神和自然的精神融和起来，意味隽永。全诗自然清淡素雅，写景抒情皆轻轻松松，然而韵味却隽永醇厚。诗人笔下的青溪是喧闹与沉郁的统一，活泼与安详的揉合，幽深与素静的融和。";

  @Test void testEncryptDecrypt() throws UnsupportedEncodingException {
    Aes aes = new Aes();

    String key = aes.generateKey(AesKeySize.KS256, ExportType.Base64);
    String iv = aes.generateIV(ExportType.Base64);

    String enc = aes.encryptCBCWithUTF8(key, iv, msg, ExportType.Base64);
    String dec = aes.decryptCBCWithUTF8(key, iv, enc, ExportType.Base64);

    assertTrue(dec.equals(msg), "encrypt decrypt successful");
  }
}
