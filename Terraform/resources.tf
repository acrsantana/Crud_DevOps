resource "aws_instance" "EC2_Instance" {
  instance_type = "t2.micro"
  ami           = "ami-007855ac798b5175e"
  tags = {
    Name = "cezaodabahia.CriadaPeloTerraform"
  }
}