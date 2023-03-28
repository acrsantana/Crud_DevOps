terraform {
  required_version = "1.4.1"
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "4.16"
    }
  }
}

provider "aws" {
  region = "us-east-1"
}