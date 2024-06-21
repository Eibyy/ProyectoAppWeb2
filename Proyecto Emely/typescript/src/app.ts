import 'reflect-metadata';
import express from 'express';
import { createConnection } from 'typeorm';
import { ApolloServer } from 'apollo-server-express';
import { buildSchema } from 'type-graphql';
import { PersonalResolver } from './schema/personalSchema';
import { RoleResolver } from './schema/roleSchema';
import personalRoutes from './routes/personalRoutes';
import roleRoutes from './routes/roleRoutes';
import authRoutes from './routes/authRoutes';
import config from './ormconfig/ormconfig';
import dotenv from 'dotenv';
import authMiddleware from './middleware/authMiddleware';

dotenv.config();

(async () => {
  await createConnection(config);

  const app = express();
  app.use(express.json());

  app.use('/auth', authRoutes);

  app.use('/personal', authMiddleware, personalRoutes);
  app.use('/roles', authMiddleware, roleRoutes);

  const schema = await buildSchema({
    resolvers: [PersonalResolver, RoleResolver],
  });

  const server = new ApolloServer({ schema });
  await server.start();
  server.applyMiddleware({ app });

  
  const PORT = process.env.PORT || 3000;
  app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
  });
})();
