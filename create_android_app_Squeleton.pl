printf "Create squeleton for android application...\n";

$num_args = $#ARGV + 1;

if($num_args < 2)
{
	printf "Please provide an app name and a location!\n"	
}

$app_name = $ARGV[0];
$path = $ARGV[1];
chdir($path) or die "Cant chdir to $path $!";

#Create top level directory...
printf "Create top level directory...\n";

unless (-e $app_name)
{
	mkdir($app_name);
}

#Move inside the newly created directory
chdir($app_name) or die "Cant chdir to $path $!";

#Create sub directories...
printf "Create sub directories...\n";
unless (-e "src")
{
	mkdir("src");
} 

unless (-e "res")
{
	mkdir("res");
} 

unless (-e "assets")
{
	mkdir("assets");
} 

#Create top level config files...
printf "Create configuration files...\n";
open  (FILE, ">>AndroidManifest.xml") or die $!;
open  (FILE, ">>Android.mk") or die $!;
