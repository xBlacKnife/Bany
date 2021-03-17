
# Imports
import boto3

class S3(object):
    
    # Creates the s3 resource
    def __init__(self, bucket):
        s3 = boto3.resource('s3')
        self.__bucket = s3.Bucket(bucket)

    # Returns true if the file exists
    def object_exists(self, file):
        objs = list(self.__bucket.objects.filter(Prefix=file))
        return len(objs) > 0

    # Uploads a file to s3
    def read_file(self, file):
        return self.__bucket.Object(file).get()['Body'].read().decode('latin1')
