import { Router } from 'express';
import { Roles } from '../models/roleModel';
import { getRepository } from 'typeorm';

const router = Router();

router.get('/', async (req, res) => {
  const roles = await getRepository(Roles).find();
  res.json(roles);
});

router.post('/', async (req, res) => {
  const role = getRepository(Roles).create(req.body);
  const result = await getRepository(Roles).save(role);
  res.json(result);
});

export default router;
