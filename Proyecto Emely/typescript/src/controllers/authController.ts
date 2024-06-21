import { Request, Response } from 'express';
import { getRepository } from 'typeorm';
import bcrypt from 'bcryptjs';
import jwt from 'jsonwebtoken';
import dotenv from 'dotenv';
import { User } from '../models/userModel';

dotenv.config();

export const registerUser = async (req: Request, res: Response) => {
  const userRepository = getRepository(User);
  const { username, password } = req.body;

  const existingUser = await userRepository.findOne({ username });
  if (existingUser) {
    return res.status(400).json({ message: 'El usuario ya existe' });
  }

  const hashedPassword = await bcrypt.hash(password, 10);
  const user = userRepository.create({ username, password: hashedPassword });

  await userRepository.save(user);
  res.status(201).json({ message: 'Usuario registrado correctamente' });
};

export const loginUser = async (req: Request, res: Response) => {
  const userRepository = getRepository(User);
  const { username, password } = req.body;

  const user = await userRepository.findOne({ username });
  if (!user) {
    return res.status(400).json({ message: 'Credenciales inválidas' });
  }

  const isPasswordValid = await bcrypt.compare(password, user.password);
  if (!isPasswordValid) {
    return res.status(400).json({ message: 'Credenciales inválidas' });
  }

  const token = jwt.sign({ id: user.id }, process.env.JWT_SECRET as string, {
    expiresIn: process.env.JWT_EXPIRES_IN,
  });

  res.json({ token });
};
