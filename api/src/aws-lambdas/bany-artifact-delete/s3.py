
# Imports
import boto3

class S3(object):
    
    # Creates the s3 client
    def __init__(self, bucket):
        s3 = boto3.resource('s3')
        self.__bucket = s3.Bucket(bucket)

    # Returns true if the folder exists
    def folder_exists(self, folder):
        objs = list(self.__bucket.objects.filter(Prefix=folder))
        return len(objs) > 0

    # Deletes a folder from the bucket
    def delete_folder(self, folder):
        self.__bucket.objects.filter(Prefix='{0}/'.format(folder)).delete()
