
# coding: utf-8

# In[3]:

import json


# In[4]:

with open('flight.json') as data_file:    
    data = json.load(data_file)
    


# In[5]:

ff = data["items"][0]['trips']["tripOption"][0]["slice"][0]["segment"][0]["flight"]


# In[6]:

str(ff['carrier'])+str(ff['number'])


# In[7]:

ll = data["items"][0]['trips']["tripOption"][0]["slice"][0]["segment"][0]["leg"]
ll


# In[8]:

carrier = dict()
for item in data["items"]:
    for f in item["trips"]["data"]['carrier']:
        carrier[f['code']] = f['name']
        
carrier.items()


# In[9]:

flights = []

for item in data["items"]:
    for f in item["trips"]["tripOption"]:
        flight = dict()
        flight['id'] = len(flights)
        flight['price'] = f['saleTotal'].split('USD')[1]
        segment = f["slice"][0]["segment"][0]
        ff = segment["flight"]
        flight['carrier'] = carrier[ff['carrier']]
        flight['flightNumber'] =str(ff['carrier'])+str(ff['number'])
        leg = segment["leg"][0]
        flight['arrivalTime'] = leg["arrivalTime"]
        flight['departureTime'] = leg['departureTime']
        flight['origin'] = leg['origin']
        flight['destination'] = leg['destination']
        
        flights.append(flight)
        
with open('formatedflight.json','w') as fp:    
    json.dump(flights,fp)
fp.close()


# In[10]:

'USD55.3'.split('USD')[1]


# In[ ]:



