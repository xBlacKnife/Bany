
# Imports
import boto3

class S3(object):
    
    # Creates the s3 resource
    def __init__(self, bucket):
        s3 = boto3.resource('s3')
        self.__bucket = s3.Bucket(bucket)

    # Returns true if the folder exists
    def object_exists(self, folder):
        objs = list(self.__bucket.objects.filter(Prefix=folder))
        return len(objs) > 0

    # Uploads a file to s3
    def upload_file(self, file, bin_content):
        object = self.__bucket.Object(file)
        object.put(Body=bin_content)
