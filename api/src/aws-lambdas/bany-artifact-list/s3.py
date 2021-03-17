
# Imports
import boto3

class S3(object):
    
    # Creates the s3 client
    def __init__(self, bucket):
        s3 = boto3.resource('s3')
        self.__bucket = s3.Bucket(bucket)

    # Returns the artifacts in bucket
    def list_folders(self):
        return list(self.__bucket.objects.all())
