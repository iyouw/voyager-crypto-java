package org.linkerdesign.crypto;

import org.junit.jupiter.api.Test;
import org.linkerdesign.crypto.abstraction.ExportType;
import org.linkerdesign.crypto.messagedigest.Md5;
import org.linkerdesign.crypto.messagedigest.Md5Sha1;
import org.linkerdesign.crypto.messagedigest.Sha1;
import org.linkerdesign.crypto.messagedigest.Sha256;
import org.linkerdesign.crypto.messagedigest.Sha384;
import org.linkerdesign.crypto.messagedigest.Sha512;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;

public class MdTest {
  private final String msg = "《青溪》是唐代诗人王维创作的一首五言古诗。此诗描写了一条青溪的幽秀景色，诗人用多彩的画笔，绘出青溪流经不同地方时呈现的不同画面。其中“声喧乱石中，色静深松里”两句，以喧响的声音和幽冷的色调形成闹与静的强烈对比，如同一幅“有声画”。诗的末四句写出诗人心境的闲谈正如清川的闲淡，把自己的精神和自然的精神融和起来，意味隽永。全诗自然清淡素雅，写景抒情皆轻轻松松，然而韵味却隽永醇厚。诗人笔下的青溪是喧闹与沉郁的统一，活泼与安详的揉合，幽深与素静的融和。";

  @Test void testSha1() throws UnsupportedEncodingException {
    Sha1 sha = new Sha1();
    String hash = sha.digest(msg, ExportType.UTF8, ExportType.Base64);
    assertTrue(hash != null, "hash is not null");
  }

  @Test void testSha256() throws UnsupportedEncodingException {
    Sha256 sha = new Sha256();
    String hash = sha.digest(msg, ExportType.UTF8, ExportType.Base64);
    assertTrue(hash != null, "hash is not null");
  }

  @Test void testSha384() throws UnsupportedEncodingException {
    Sha384 sha = new Sha384();
    String hash = sha.digest(msg, ExportType.UTF8, ExportType.Base64);
    assertTrue(hash != null, "hash is not null");
  }

  @Test void testSha512() throws UnsupportedEncodingException {
    Sha512 sha = new Sha512();
    String hash = sha.digest(msg, ExportType.UTF8, ExportType.Base64);
    assertTrue(hash != null, "hash is not null");
  }

  @Test void testMd5() throws UnsupportedEncodingException {
    Md5 md = new Md5();
    String hash = md.digest(msg, ExportType.UTF8, ExportType.Base64);
    assertTrue(hash != null, "hash is not null");
  }

  @Test void testMd5Sha1() throws UnsupportedEncodingException {
    Md5Sha1 md = new Md5Sha1();
    String hash = md.digest(msg, ExportType.UTF8, ExportType.Base64);
    assertTrue(hash != null, "hash is not null");
  }
}
