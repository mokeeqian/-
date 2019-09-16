#!/usr/bin/env python3
# encoding=utf-8
# Copyright: Qian Jipeng(C) 2019

import configparser
cf = configparser.ConfigParser()

cf.read("config.conf")
section = cf.sections() # a list
# print(section)
# print(cf.options('user'))

def getUserId():
	return str(cf.get('user', 'userid'))

def getUserPassword():
	return str(cf.get('user', 'password'))

def getIndexUrl():
	return str(cf.get('web', 'index'))

def getLoginUrl():
	return str(cf.get('web', 'loginurl'))

def getCheckcodeUrl():
	return str(cf.get('web', 'checkcodeurl'))
