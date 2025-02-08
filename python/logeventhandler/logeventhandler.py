import json
import gzip
import base64
import gzip
import os
import sys
# import subprocess

# uncomment for installing the packages directly from lambda
#subprocess.call('pip install requests -t /tmp/ --no-cache-dir'.split())
sys.path.insert(1, '/var/task/logeventhandler/package/')
import requests


def lambda_handler(event, context):
    # Log the entire event for debugging
    print("Received event:")
    print(f"Logging event: {event}")
    print(f"Awslog: {event['awslogs']}")
    cw_data = event['awslogs']['data']
    print(f'data: {cw_data}')
    print(f'type: {type(cw_data)}')

    # replace with respective Splunk HEC URL
    splunk_hec_url = os.environment.get('splunk_hec_url')

    # replace with respective Splunk token
    splunk_token = os.environment.get('splunk_token')

    # replace with respective Splunk index
    splunk_index = os.environment.get('splunk_index')

    # retrieve compressed payload from the event
    compressed_payload = base64.b64decode(cw_data)

    # extract uncompressed payload
    uncompressed_payload = gzip.decompress(compressed_payload)

    # convert the payload to JSON
    payload = json.loads(uncompressed_payload)

    # some debug statements
    log_events = payload['logEvents']
    for log_event in log_events:
        print(f'LogEvent: {log_event}')

    # splunk payload
    splunk_payload = {
        "event" : json_payload,
        "index" : splunk_index
    }

    # splunk headers
    splunk_headers = {'Authorization' : f'Splunk {splunk_token}', 'Content-Type': 'application/json'}

    response = requests.post(splunk_hec_url, headers=splunk_headers, json=splunk_payload)
    response.close()
    print(f"Log sent to Splunk. Response: {response.text}")


