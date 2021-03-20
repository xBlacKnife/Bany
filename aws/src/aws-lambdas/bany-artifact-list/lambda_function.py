import json
from s3 import S3

def get_artifacts(files):
    objects = dict()
    for file in files:
        tokens = file.key.split('/')
        artifact = tokens[0]
        document = tokens[1]
        
        if artifact not in objects:
            objects[artifact] = list()
    
        if len(document) > 0:
            objects[artifact].append(document)
    
    result = list()
    for key in objects:
        obj = dict()
        obj['name'] = key
        
        for item in objects[key]:
            if item.endswith('.txt'):
                obj['description'] = item
            elif item.endswith('.png'):
                obj['image'] = item
            elif item.endswith('.flac'):    
                obj['audio'] = item
        
        result.append(obj)
    
    return result
    
def lambda_handler(event, context):
    response = dict()
    return_code = 200

    try:
        s3 = S3('bany-bucket')
        files = s3.list_folders()
        
        response['artifacts'] = get_artifacts(files)

    except Exception as e:
        return_code = 500
        response['error'] = str(e)
    
    response['code'] = return_code
    
    return response