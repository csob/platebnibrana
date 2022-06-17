<pre>

<?php
require_once ('logger.php');
require_once ('crypto.php');

echo "sign & verify test ...\n";

$text = "some text to sign";
$private_key = "rsa_A1029DTmM7.key";
$private_key_passwd = null;
$public_key = "rsa_A1029DTmM7.pub";

echo "signing text '" . htmlspecialchars($text) . "' using private key " . htmlspecialchars($private_key) . "\n";
$signature = sign($text, $private_key, $private_key_passwd);
echo "signature is '" . htmlspecialchars($signature) . "'\n";

echo "verifying signature using public key " . htmlspecialchars($public_key) . "\n";
$result = verify($text, $signature, $public_key);
echo "verify result: " . ($result == 1 ? "ok" : "failed") . "\n";

?>
</pre>